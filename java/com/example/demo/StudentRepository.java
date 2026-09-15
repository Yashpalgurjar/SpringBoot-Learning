package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import jakarta.transaction.Transactional;

public interface StudentRepository extends JpaRepository<StudentData, Long> {

    List<StudentData> findByCity(String city);

    List<StudentData> findByAgeGreaterThan(int age);
    Page<StudentData> findAll(Pageable pageable);

    @Query("""
           SELECT s
           FROM StudentData s
           WHERE s.age > :age
           """)
    List<StudentData> findStudentsByAgeJPQL(
            @Param("age") int age);

    @Query("""
           SELECT s
           FROM StudentData s
           WHERE s.city = :city
           AND s.age > :age
           """)
    List<StudentData> findByCityAndAgeJPQL(
            @Param("city") String city,
            @Param("age") int age);

    @Query("""
           SELECT s
           FROM StudentData s
           WHERE s.city = :city
           OR s.age > :age
           """)
    List<StudentData> findByCityOrAgeJPQL(
            @Param("city") String city,
            @Param("age") int age);

    @Query("""
           SELECT s
           FROM StudentData s
           ORDER BY s.age ASC
           """)
    List<StudentData> findStudentsSortedByAgeJPQL();

    @Query("""
           SELECT s
           FROM StudentData s
           ORDER BY s.age DESC
           """)
    List<StudentData> findStudentsSortedByAgeDescJPQL();

    @Query("""
           SELECT s
           FROM StudentData s
           WHERE s.name LIKE CONCAT('%', :name, '%')
           """)
    List<StudentData> searchStudentsByNameJPQL(
            @Param("name") String name);

    @Query("""
           SELECT s
           FROM StudentData s
           WHERE s.age BETWEEN :minAge AND :maxAge
           """)
    List<StudentData> findStudentsByAgeRangeJPQL(
            @Param("minAge") int minAge,
            @Param("maxAge") int maxAge);

    @Query("""
           SELECT s
           FROM StudentData s
           WHERE s.city IN :cities
           """)
    List<StudentData> findStudentsByCitiesJPQL(
            @Param("cities") List<String> cities);

    @Query("""
           SELECT s
           FROM StudentData s
           WHERE s.city IS NOT NULL
           """)
    List<StudentData> findStudentsWithNonNullCityJPQL();

    @Query("""
           SELECT s
           FROM StudentData s
           WHERE s.city NOT IN :cities
           """)
    List<StudentData> findStudentsNotInCitiesJPQL(
            @Param("cities") List<String> cities);

    @Query("""
           SELECT s
           FROM StudentData s
           WHERE s.name NOT LIKE CONCAT('%', :name, '%')
           """)
    List<StudentData> findStudentsNameNotLikeJPQL(
            @Param("name") String name);

    @Query("""
           SELECT s
           FROM StudentData s
           WHERE s.age >= :age
           """)
    List<StudentData> findStudentsByAgeGreaterOrEqualJPQL(
            @Param("age") int age);

    @Query("""
           SELECT COUNT(s)
           FROM StudentData s
           """)
    long countAllStudentsJPQL();

    @Query("""
           SELECT s
           FROM StudentData s
           """)
    List<StudentData> findAllStudentsJPQL();

    @Query("""
           SELECT s
           FROM StudentData s
           WHERE s.age = :age
           """)
    List<StudentData> findStudentsByAge(
            @Param("age") int age);

    @Query("""
           SELECT s
           FROM StudentData s
           WHERE s.age > :age
           """)
    List<StudentData> findStudentsByAgeGreaterThan(
            @Param("age") int age);

    @Query("""
           SELECT AVG(s.age)
           FROM StudentData s
           """)
    double findAverageAge();

    @Query("""
           SELECT SUM(s.age)
           FROM StudentData s
           """)
    long findTotalAge();

    @Query("""
           SELECT MIN(s.age)
           FROM StudentData s
           """)
    int findMinimumAge();

    @Query("""
           SELECT MAX(s.age)
           FROM StudentData s
           """)
    int findMaximumAge();

    @Query("""
           SELECT DISTINCT s
           FROM StudentData s
           JOIN s.courses c
           """)
    List<StudentData> findStudentsWithCoursesJPQL();
    @Query("""
    	       SELECT s.city, COUNT(s)
    	       FROM StudentData s
    	       GROUP BY s.city
    	       """)
    	List<Object[]> countStudentsByCity();
    	@Query("""
    		       SELECT s.city, COUNT(s)
    		       FROM StudentData s
    		       GROUP BY s.city
    		       HAVING COUNT(s) > 2
    		       """)
    		List<Object[]> citiesWithMoreThanTwoStudents();
    		@Modifying
    		@Transactional
    		@Query("""
    		       UPDATE StudentData s
    		       SET s.city = :city
    		       WHERE s.id = :id
    		       """)
    		int updateStudentCity(
    		        @Param("id") Long id,
    		        @Param("city") String city);
    		@Modifying
    		@Transactional
    		@Query("""
    		       DELETE FROM StudentData s
    		       WHERE s.id = :id
    		       """)
    		int deleteStudentByIdJPQL(
    		        @Param("id") Long id);
    	

}