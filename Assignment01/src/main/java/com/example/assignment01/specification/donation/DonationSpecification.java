package com.example.assignment01.specification.donation;

import com.example.assignment01.entity.Donation;
import com.example.assignment01.utils.Utils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;

public class DonationSpecification {

    @SuppressWarnings("deprecation")
    public static Specification<Donation> buildWhere(String search) {

        Specification<Donation> where = null;

        if (!StringUtils.isEmpty(search)) {

            search = Utils.formatSearch(search);

            CustomSpecification status = new CustomSpecification("status", search);
            where = Specification.where(status);
        }
        return where;
    }
}

    @SuppressWarnings("serial")
    @RequiredArgsConstructor
    class CustomSpecification implements Specification<Donation> {

        @NonNull
        private String field;
        @NonNull
        private Object value;

        @Override
        public Predicate toPredicate(Root<Donation> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

            if (field.equalsIgnoreCase("status")) {
                String  str = value.toString();
                String[] arr = str.split("");
                Expression<String> pa = root.get("status");
                return pa.in(arr );
            }
            return null;
        }
    }

