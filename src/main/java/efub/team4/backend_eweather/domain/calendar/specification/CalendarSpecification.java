package efub.team4.backend_eweather.domain.calendar.specification;

import efub.team4.backend_eweather.domain.calendar.entity.Calendar;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class CalendarSpecification implements Specification<Calendar> {
    private final CalendarSearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Calendar> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (criteria.getUserId() != null){
            predicates.add(criteriaBuilder.equal(root.join("user").get("id"), criteria.getUserId()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
