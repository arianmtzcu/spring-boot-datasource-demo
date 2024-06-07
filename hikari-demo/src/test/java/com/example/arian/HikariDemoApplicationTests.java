package com.example.arian;

import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class HikariDemoApplicationTests {

   @Autowired
   private ApplicationContext context;

   @Test
   void contextLoads() {
      assert Objects.nonNull(context);
      assert context.containsBean("dataSource");
   }

}
