package exercise.repository;

import exercise.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
// Репозиторий для основной задачи
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
}

// Дополнительная задача
// Если решите сделать дополнительную задачу, измените существующий репозиторий для работы с Querydsl предикатами
// BEGIN
//public interface UserRepository extends
//        JpaRepository<User, Long>,
//        QuerydslPredicateExecutor<User>,
//        QuerydslBinderCustomizer<QUser> {
//
//    @Override
//    default void customize(QuerydslBindings bindings, QUser user) {
//        // ...
//    }
//}
// END
