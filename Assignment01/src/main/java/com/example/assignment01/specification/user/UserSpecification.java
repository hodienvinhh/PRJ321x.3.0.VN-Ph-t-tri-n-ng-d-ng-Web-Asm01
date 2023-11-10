package com.example.assignment01.specification.user;

import com.example.assignment01.entity.User;
import com.example.assignment01.utils.Utils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserSpecification {

    @SuppressWarnings("deprecation")
    public static Specification<User> buildWhere(String search) {

        Specification<User> where = null;

        if (!StringUtils.isEmpty(search)) {

            search = Utils.formatSearch(search);

            CustomSpecification email = new CustomSpecification("email", search);
            CustomSpecification phoneNumber = new CustomSpecification("phoneNumber", search);
            where = Specification.where(email).or(phoneNumber);
        }
        return where;
    }
}

    @SuppressWarnings("serial")
    @RequiredArgsConstructor
    class CustomSpecification implements Specification<User> {

        @NonNull
        private String field;
        @NonNull
        private Object value;

        @Override
        public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

            if (field.equalsIgnoreCase("email")) {
                return criteriaBuilder.like(root.get("email"), "%" + value.toString() + "%");
            }

            if (field.equalsIgnoreCase("phoneNumber")) {
                return criteriaBuilder.like(root.get("phoneNumber"), "%" + value.toString() + "%");
            }
            return null;
        }
    }

