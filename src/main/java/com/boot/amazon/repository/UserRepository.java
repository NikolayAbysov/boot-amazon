package com.boot.amazon.repository;

import com.boot.amazon.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByProfileName(String profileName);

    @Query(value = "select pns.profile_name "
            + "from (select \"user\".profile_name, count(summary) as s "
            + "      from \"user\" "
            + "           left join review on \"user\".id = review.user_id "
            + "      group by  \"user\".profile_name "
            + "      order by s desc "
            + "      limit 1000) as pns", nativeQuery = true)
    List<String> getTopThousandMostActiveUsers();
}
