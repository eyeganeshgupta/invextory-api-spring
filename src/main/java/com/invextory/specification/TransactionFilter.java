package com.invextory.specification;

import com.invextory.models.Transaction;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TransactionFilter {

    private static final Logger logger = LoggerFactory.getLogger(TransactionFilter.class);

    public static Specification<Transaction> byFilter(String searchValue) {
        return (root, query, criteriaBuilder) -> {
            if (searchValue == null || searchValue.trim().isEmpty()) {
                logger.info("Search value is empty or null. Returning all transactions.");
                return criteriaBuilder.conjunction();
            }

            String searchPattern = "%" + searchValue.toLowerCase() + "%";
            List<Predicate> predicates = new ArrayList<>();

            logger.info("Starting transaction filtering with search value: {}", searchValue);

            // Local Transaction fields
            logger.info("Applying filter on Transaction fields: description, note, status, transactionType");
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("note")), searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("status").as(String.class)), searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("transactionType").as(String.class)), searchPattern));

            // Join with User
            logger.info("Joining and filtering on User fields: name, email, phoneNumber");
            Join<?, ?> userJoin = root.join("user", JoinType.LEFT);
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(userJoin.get("name")), searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(userJoin.get("email")), searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(userJoin.get("phoneNumber")), searchPattern));

            // Join with Supplier
            logger.info("Joining and filtering on Supplier fields: name, contactInfo");
            Join<?, ?> supplierJoin = root.join("supplier", JoinType.LEFT);
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(supplierJoin.get("name")), searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(supplierJoin.get("contactInfo")), searchPattern));

            // Join with ProductBatch
            logger.info("Joining and filtering on ProductBatch field: batchNumber");
            Join<?, ?> batchJoin = root.join("productBatch", JoinType.LEFT);
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(batchJoin.get("batchNumber")), searchPattern));

            // Join with Product from ProductBatch
            logger.info("Joining and filtering on Product fields: name, sku, description");
            Join<?, ?> productJoin = batchJoin.join("product", JoinType.LEFT);
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(productJoin.get("name")), searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(productJoin.get("sku")), searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(productJoin.get("description")), searchPattern));

            // Join with Category from Product
            logger.info("Joining and filtering on Category field: name");
            Join<?, ?> categoryJoin = productJoin.join("category", JoinType.LEFT);
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(categoryJoin.get("name")), searchPattern));

            logger.info("Completed building specification with {} predicates.", predicates.size());
            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }
}
