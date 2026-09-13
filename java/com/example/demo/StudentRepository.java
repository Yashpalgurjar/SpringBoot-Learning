  package com.example.demo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface StudentRepository
        extends JpaRepository<StudentData, Long> {

    List<StudentData> findByCity(String city);
    List<StudentData> findByAgeGreaterThan(int age);
    @Query("SELECT s FROM StudentData s WHERE s.age > :age")
    List<StudentData> findStudentsByAgeJPQL(@Param("age") int age);
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
    	List<StudentData> findStudentsWithNonNullCityJPQL();@Query("""
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
}