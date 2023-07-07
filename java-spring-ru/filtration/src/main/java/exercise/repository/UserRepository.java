package exercise.repository;

import com.querydsl.core.types.dsl.StringExpression;
import exercise.model.QUser;
import exercise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

//@Repository
//// Репозиторий для основной задачи
//public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
//}

// Дополнительная задача
// Если решите сделать дополнительную задачу, измените существующий репозиторий для работы с Querydsl предикатами
// BEGIN
@Repository
public interface UserRepository extends
        JpaRepository<User, Long>,
        QuerydslPredicateExecutor<User>,
        QuerydslBinderCustomizer<QUser> {

    @Override
    default void customize(QuerydslBindings bindings, QUser user) {
        bindings.bind(user.firstName, user.lastName, user.email, user.profession).first(
                StringExpression::containsIgnoreCase
        );
    }
}
// END
