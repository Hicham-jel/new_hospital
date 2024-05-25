package ma.emsi.springangular;

import ma.emsi.springangular.entities.Payment;
import ma.emsi.springangular.entities.PaymentStatus;
import ma.emsi.springangular.entities.PaymentType;
import ma.emsi.springangular.entities.Student;
import ma.emsi.springangular.repository.PaymentRepository;
import ma.emsi.springangular.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class SpringAngularApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAngularApplication.class, args);
    }
   @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository, PaymentRepository paymentRepository) {
        return args->{
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).firstName("Hicham").code("123").programId("Miage").build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).firstName("Amine").code("124").programId("Industriel").build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).firstName("Samia").code("125").programId("Miage").build());
            studentRepository.save(Student.builder().id(UUID.randomUUID().toString()).firstName("Hassan").code("126").programId("Génie Civil").build());
            Random random = new Random();
            PaymentType[] paymentTypes = PaymentType.values();
            studentRepository.findAll().forEach(st->{
                for (int i=0;i<10;i++)
                {
                    int index=random.nextInt(paymentTypes.length);
                    Payment payment= Payment.builder().amount(1000+(int)(Math.random()+2000)).type(paymentTypes[index])
                            .status(PaymentStatus.CREATED)
                            .date(LocalDate.now())
                            .student(st)
                            .build();
                    paymentRepository.save(payment);
                }
            });
        };
   }
}
