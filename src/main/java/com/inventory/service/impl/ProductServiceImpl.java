package com.inventory.service.impl;

import com.inventory.constant.AppConstants;
import com.inventory.dto.request.ProductRequest;
import com.inventory.dto.response.ProductResponse;
import com.inventory.entity.Category;
import com.inventory.entity.Product;
import com.inventory.exception.ResourceNotFoundException;
import com.inventory.mapper.ProductMapper;
import com.inventory.repository.CategoryRepository;
import com.inventory.repository.ProductRepository;
import com.inventory.service.ProductService;
import com.inventory.util.BarcodeGenerator;
import com.inventory.util.ExcelHelper;
import com.inventory.util.QrCodeGenerator;
import com.inventory.util.SkuGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ProductMapper productMapper;

    private final SkuGenerator skuGenerator;

    private final BarcodeGenerator barcodeGenerator;

    private final QrCodeGenerator qrCodeGenerator;

    private final ExcelHelper excelHelper;

    @Override
    public ProductResponse create(ProductRequest request) {

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.CATEGORY_NOT_FOUND));

        String sku = skuGenerator.generateSku(
                request.getName(),
                productRepository.count()
        );

        String barcode = barcodeGenerator.generateBarcode(sku);

        String qr = qrCodeGenerator.generateQrCode(
                sku,
                "Name: " + request.getName()
                        + "\nSKU: " + sku
                        + "\nSelling Price: " + request.getSellingPrice()
        );

        Product product = Product.builder()
                .name(request.getName())
                .sku(sku)
                .sellingPrice(request.getSellingPrice())
                .costPrice(request.getCostPrice())
                .description(request.getDescription())
                .barcode(barcode)
                .qrCode(qr)
                .category(category)
                .build();

        Product savedProduct = productRepository.save(product);

        return productMapper.toResponse(savedProduct);

    }

    @Override
    public ProductResponse getById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.PRODUCT_NOT_FOUND));

        return productMapper.toResponse(product);

    }

    @Override
    public List<ProductResponse> getAll() {

        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .toList();

    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.PRODUCT_NOT_FOUND));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.CATEGORY_NOT_FOUND));

        product.setName(request.getName());
        product.setSellingPrice(request.getSellingPrice());
        product.setCostPrice(request.getCostPrice());
        product.setDescription(request.getDescription());
        product.setCategory(category);

        Product updated = productRepository.save(product);

        return productMapper.toResponse(updated);

    }

    @Override
    public void delete(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(AppConstants.PRODUCT_NOT_FOUND));

        productRepository.delete(product);

    }

    @Override
    public ByteArrayInputStream exportProducts() {

        List<Product> products =
                productRepository.findAll();

        List<String[]> rows = products.stream()
                .map(product -> new String[]{

                        String.valueOf(product.getId()),
                        product.getName(),
                        product.getSku(),
                        String.valueOf(product.getSellingPrice()),
                        String.valueOf(product.getCostPrice()),
                        product.getCategory().getName()
                })
                .toList();

        return excelHelper.productsToExcel(rows);
    }
    @Override
    public void importProducts(MultipartFile file) {

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                if (row == null) {
                    continue;
                }

                String name = row.getCell(0).getStringCellValue();

                Double sellingPrice = row.getCell(1).getNumericCellValue();

                Double costPrice = row.getCell(2).getNumericCellValue();

                String description = row.getCell(3).getStringCellValue();

                String categoryName = row.getCell(4).getStringCellValue();

                Category category = categoryRepository
                        .findByName(categoryName)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Category not found : " + categoryName));

                String sku = skuGenerator.generateSku(
                        name,
                        productRepository.count());

                String barcode =
                        barcodeGenerator.generateBarcode(sku);

                String qr =
                        qrCodeGenerator.generateQrCode(
                                sku,
                                "Name : " + name
                                        + "\nSKU : " + sku
                                        + "\nSelling Price : " + sellingPrice);

                Product product = Product.builder()
                        .name(name)
                        .sellingPrice(BigDecimal.valueOf(sellingPrice))
                        .costPrice(BigDecimal.valueOf(costPrice))
                        .description(description)
                        .sku(sku)
                        .barcode(barcode)
                        .qrCode(qr)
                        .category(category)
                        .build();

                productRepository.save(product);

            }

        } catch (Exception e) {

            throw new RuntimeException("Excel Import Failed", e);

        }

    }
    @Override
    public List<ProductResponse> search(String keyword) {

        return productRepository
                .findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(productMapper::toResponse)
                .toList();

    }


}