//package com.studentapp.studentinfo;
//
//import com.studentapp.testbase.TestBase;
//import com.studentapp.utils.TestUtils;
//import io.restassured.response.ValidatableResponse;
//import net.serenitybdd.junit.runners.SerenityRunner;
//import net.thucydides.core.annotations.Steps;
//import net.thucydides.core.annotations.Title;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import static org.hamcrest.Matchers.hasValue;
//
///**
// * Created by Jay
// */
//@RunWith((SerenityRunner.class))
//public class StudentCURDTestWithSteps extends TestBase {
//
//
//        static String firstName = "PrimUser" + TestUtils.getRandomValue();
//        static String lastName = "PrimeUser" + TestUtils.getRandomValue();
//        static String programme = "Api Testing";
//        static String email = TestUtils.getRandomValue() + "xyz@gmail.com";
//        static int studentId;
//
//        @Steps
//        StudentSteps studentSteps;
//
//
//        @Title("This will create a new student")
//        @Test
//        public void test001() {
//            List<String> courseList = new ArrayList<>();
//            courseList.add("Scala");
//            courseList.add("Java");
//            ValidatableResponse response = studentSteps.createStudent(firstName, lastName, email, programme, courseList);
//            response.log().all().statusCode(201);
//        }
//
//
//
//        @Title("Verify if the student was added to the application")
//        @Test
//        public void test002() {
//            HashMap<String,Object>studentMap=studentSteps.getStudentInfByFirstname(firstName);
//            Assert.assertThat(studentMap, hasValue(firstName));
//            studentId = (int) studentMap.get("id");
//            System.out.println(studentId);
//        }
//
//
//
//        @Title("Update the user information and verify the updated information")
//        @Test
//        public void test003() {
//            firstName = firstName + "_updated";
//            List<String> courseList = new ArrayList<>();
//            courseList.add("Scala");
//            courseList.add("Java");
//            studentSteps.updateStudent(studentId,firstName,lastName,email,programme,courseList).log().all().statusCode(200);
//            //63 line replace with the 65 and 66
////            ValidatableResponse response = studentSteps.updateStudent(studentId,firstName,lastName,email,programme,courseList);
////            response.log().all().statusCode(200);
//            //above two line is replace with 66 to 78 line
////            StudentPojo studentPojo = new StudentPojo();
////            studentPojo.setFirstName(firstName);
////            studentPojo.setLastName(lastName);
////            studentPojo.setEmail(email);
////            studentPojo.setProgramme(programme);
////            studentPojo.setCourses(courseList);
////            SerenityRest.given().log().all()
////                    .header("Content-Type", "application/json")
////                    .pathParam("studentID", studentId)
////                    .body(studentPojo)
////                    .when()
////                    .put(EndPoints.UPDATE_STUDENT_BY_ID)
////                    .then().log().all().statusCode(200);
//            HashMap<String,Object>studentMap=studentSteps.getStudentInfByFirstname(firstName);
//            Assert.assertThat(studentMap, hasValue(firstName));
//        }
//
//
//
//
//        @Title("Delete the student and verify if the student is deleted!")
//        @Test
//        public void test004() {
//            studentSteps.deleteStudent(studentId).statusCode(204);
//            //replace 89 to 94 with 87
////            SerenityRest.given().log().all()
////                    .pathParam("studentID", studentId)
////                    .when()
////                    .delete(EndPoints.DELETE_STUDENT_BY_ID)
////                    .then().statusCode(204);
//            studentSteps.getStudentById(studentId).statusCode(404);
//            //96 to 101 is replace by 94
////            SerenityRest.given().log().all()
////                    .pathParam("studentID", studentId)
////                    .when()
////                    .get(EndPoints.GET_SINGLE_STUDENT_BY_ID)
////                    .then()
////                    .statusCode(404);
//
//        }
//
//
//    }

package com.bestbuyapi.categoriesinfo;


import com.bestbuyapi.bestbuyapiinfo.CategoriesSteps;

import com.bestbuyapi.testbase.CategoriesTestBase;
import com.bestbuyapi.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class categoriesCURDTestWithSteps extends CategoriesTestBase {

    static String name = "AAA Batteries";
    static String id = TestUtils.getRandomName();

    static String categoriesId;

    @Steps
    CategoriesSteps categoriesSteps;

    @Title("This will create a new category")
    @Test
    public void test01() {
        categoriesSteps.createCategory(name, id).statusCode(201).log().all();

    }

    @Title("Verify if the category was created")
    @Test
    public void test02() {

        HashMap<String, Object> value = categoriesSteps.getCategoryInfoByName(name);
        Assert.assertThat(value, hasValue(name));
        categoriesId = (String) value.get("id");
        System.out.println(categoriesId);
    }

    @Title("Update the category information and verify the updated information")
    @Test
    public void test03() {
        name = name + "_updated";
        categoriesSteps.updateCategory(name, categoriesId).statusCode(200).log().all();
        HashMap<String, Object> categoryMap = categoriesSteps.getCategoryInfoByName(name);
        Assert.assertThat(categoryMap, hasValue(name));

    }

    @Title("Delete the category and verify if the category is deleted!")
    @Test
    public void test04() {
        categoriesSteps.deleteCategory(categoriesId).statusCode(200);
        categoriesSteps.getCategoryById(categoriesId).statusCode(404);
    }

}
