package efub.team4.backend_eweather.domain.vote.specification;

import efub.team4.backend_eweather.domain.vote.entity.Votes;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class VotesSpecification implements Specification<Votes> {
    private final VoteSearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Votes> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (criteria.getUserId() != null) {
            predicates.add(criteriaBuilder.equal(root.join("user").get("id"), criteria.getUserId()));
        }

        if (criteria.getVotePostsId() != null) {
            predicates.add(criteriaBuilder.equal(root.join("votePosts").get("id"), criteria.getVotePostsId()));
        }

        if (criteria.getIsApproved() != null) {
            System.out.println(root.get("isApproved"));
            predicates.add(criteriaBuilder.equal(root.get("isApproved"), criteria.getIsApproved()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
