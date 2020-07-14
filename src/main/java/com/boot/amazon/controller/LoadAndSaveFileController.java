package com.boot.amazon.controller;

import com.boot.amazon.dto.CsvRecordDto;
import com.boot.amazon.mapper.ProductMapper;
import com.boot.amazon.mapper.ReviewMapper;
import com.boot.amazon.mapper.UserMapper;
import com.boot.amazon.model.Product;
import com.boot.amazon.model.Review;
import com.boot.amazon.model.User;
import com.boot.amazon.service.FileService;
import com.boot.amazon.service.ParseService;
import com.boot.amazon.service.ProductSevice;
import com.boot.amazon.service.ReviewService;
import com.boot.amazon.service.UserService;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class LoadAndSaveFileController {
    private static final String LOAD_FROM_URL =
            "https://spring-boot-aws-revievers.s3.eu-central-1.amazonaws.com/Reviews.csv";
    private static final String SAVE_TO_PATH = "file.csv";
    private final FileService fileService;
    private final ParseService<CsvRecordDto> parseService;
    private final ProductMapper productMapper;
    private final ReviewMapper reviewMapper;
    private final UserMapper userMapper;
    private final ProductSevice productSevice;
    private final UserService userService;
    private final ReviewService reviewService;

    public LoadAndSaveFileController(FileService fileService,
                                     ParseService<CsvRecordDto> parseService,
                                     ProductMapper productMapper, ReviewMapper reviewMapper,
                                     UserMapper userMapper, ProductSevice productSevice,
                                     UserService userService, ReviewService reviewService) {
        this.fileService = fileService;
        this.parseService = parseService;
        this.productMapper = productMapper;
        this.reviewMapper = reviewMapper;
        this.userMapper = userMapper;
        this.productSevice = productSevice;
        this.userService = userService;
        this.reviewService = reviewService;
    }

    @GetMapping("/load")
    public String loadFile() throws IOException {
        fileService.saveFileFromUrl(LOAD_FROM_URL, SAVE_TO_PATH);
        return "File loaded";
    }

    @GetMapping("/save")
    public String saveFile() throws IOException {
        List<CsvRecordDto> parse = parseService.parse(new File(SAVE_TO_PATH));
        Map<User, User> users = new HashMap();
        Map<Product, Product> products = new HashMap();
        Set<Review> reviews = new HashSet<>();

        for (CsvRecordDto recordDto : parse) {
            User user = userMapper.map(recordDto);
            if (!users.containsKey(user)) {
                users.put(user, user);
            } else {
                user = users.get(user);
            }
            Product product = productMapper.map(recordDto);
            if (!products.containsKey(product)) {
                products.put(product, product);
            } else {
                product = products.get(product);
            }
            Review review = reviewMapper.map(recordDto, user, product);
            reviews.add(review);
        }

        List<User> userList = new ArrayList<>(users.values());
        List<Product> productList = new ArrayList<>(products.values());
        userService.saveAll(userList);
        productSevice.saveAll(productList);
        reviewService.saveAll(reviews);
        return "File saved to database";
    }
}
