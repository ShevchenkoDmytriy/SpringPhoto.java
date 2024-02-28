package com.example.demo.services;

import com.example.demo.models.Image;
import com.example.demo.models.Product;
import com.example.demo.repositories.ImageRepository;
import com.example.demo.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ImageRepository imageRepository;

    public List<Product> listProducts(String title) {
        if (title != null && !title.isEmpty()) {
            return productRepository.findByTitle(title);
        }
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void addProduct(Product product, List<MultipartFile> images) throws IOException {
        for (MultipartFile imageFile : images) {
            Image image = new Image();
            image.setName(imageFile.getOriginalFilename());
            image.setType(imageFile.getContentType());
            image.setData(imageFile.getBytes());
            imageRepository.save(image);
            product.getImages().add(image);
        }
        productRepository.save(product);
    }

    // Додаткові методи для оновлення, видалення та ін.
}


