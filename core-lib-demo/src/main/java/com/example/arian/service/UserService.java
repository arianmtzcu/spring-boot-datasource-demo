package com.example.arian.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.stereotype.Service;

import com.example.arian.entity.User;
import com.example.arian.repository.UserRepository;

@Service
public class UserService {

   private final UserRepository userRepository;

   public UserService(final UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   public List<User> getAllUsers() {
      return userRepository.findAll();
   }

   public User createUser(final User user) {
      return userRepository.save(user);
   }

   public User createRandomUser() {
      final int number = new Random().nextInt((10000000 - 100) + 1) + 100;
      final User user = new User();
      user.setName(String.format("John Doe.%s", number));
      user.setEmail(String.format("johndoe.%s@arian.example.com", number));
      return userRepository.save(user);
   }

   public String stressTest(final int numberThreads, final int batchSize, final int totalNumberOfInsertions) {
      final List<Future<String>> futures = new ArrayList<>();
      final ExecutorService executorService = Executors.newFixedThreadPool(numberThreads);

      for (int i = 0; i < numberThreads; i++) {
         final int start = i * (totalNumberOfInsertions / numberThreads);
         final int end = start + (totalNumberOfInsertions / numberThreads);

         Callable<String> task = () -> {
            for (int j = start; j < end; j++) {
               final List<User> users = new ArrayList<>();
               for (int k = 0; k < batchSize && j + k < end; k++) {
                  User user = new User();
                  user.setName("User " + (j + k));
                  user.setEmail("user" + (j + k) + "@arian.example.com");
                  users.add(user);
               }

               userRepository.saveAll(users); // Batch insert
            }
            return "Batch completed by thread :: " + Thread.currentThread().getName();
         };

         futures.add(executorService.submit(task));
      }

      final StringBuilder result = new StringBuilder("Stress test completed with the following results:\n");
      for (final Future<String> future : futures) {
         try {
            result.append(future.get()).append("\n");
         } catch (InterruptedException | ExecutionException e) {
            result.append("Thread execution error: ").append(e.getMessage()).append("\n");
         }
      }
      executorService.shutdown();
      return String.valueOf(result);
   }
}
