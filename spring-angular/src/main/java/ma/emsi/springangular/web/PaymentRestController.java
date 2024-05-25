package ma.emsi.springangular.web;

import ma.emsi.springangular.entities.Payment;
import ma.emsi.springangular.entities.PaymentStatus;
import ma.emsi.springangular.entities.PaymentType;
import ma.emsi.springangular.entities.Student;
import ma.emsi.springangular.repository.PaymentRepository;
import ma.emsi.springangular.repository.StudentRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class PaymentRestController {
    private StudentRepository studentRepository;
    private PaymentRepository paymentRepository;
    public PaymentRestController(StudentRepository studentRepository, PaymentRepository paymentRepository) {
        this.studentRepository = studentRepository;
        this.paymentRepository = paymentRepository;
    }
    @GetMapping(path="/payments")
    public List<Payment> AllPayments() {
        return paymentRepository.findAll();
    }

    @GetMapping(path="/students/{code}/payments")
    public List<Payment> paymentByStudent(@PathVariable String code) {
        return paymentRepository.findByStudentCode(code);
    }

    @GetMapping(path="/payments/ByStatus")
    public List<Payment> paymentByStatus(@RequestParam PaymentStatus status) {
        return paymentRepository.findByStatus(status);
    }

    @GetMapping(path="/payments/Bytype")
    public List<Payment> paymentByType(@RequestParam PaymentType type) {
        return paymentRepository.findByType(type);
    }

    @GetMapping(path="/payments/{id}")
    public Payment getPaymentById(@PathVariable Long id) {
        return paymentRepository.findById(id).get();}

    @GetMapping(path = "/students")
    public List<Student> allStudents(){
        return  studentRepository.findAll();
    }

    @GetMapping(path="/payments/{code}")
    public Student getStudentByCode(@PathVariable String code) {
        return studentRepository.findByCode(code);
    }

    @GetMapping(path = "/studentsByprogram/{programId}")
    public List<Student> getStudentsByProgramId(@RequestParam String programId) {
        return studentRepository.findByProgramId(programId);
    }

    @PutMapping("/payments/{id}")
    public Payment updatePaymentStatus(@RequestParam PaymentStatus paymentStatus,@PathVariable Long id) {
        Payment payment = paymentRepository.findById(id).get();
        payment.setStatus(paymentStatus);
        return paymentRepository.save(payment);
    }
    @PostMapping(path = "/payments",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)//ajout
    public Payment savePayment(@RequestParam  MultipartFile file, LocalDate date,double amount ,PaymentType paymentType,String code) throws IOException {
        Path folderPath = Paths.get(System.getProperty("user.home"), "data-e", "payments");
        if (Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }
        String fileName = UUID.randomUUID().toString();
        Path filePath = Paths.get(System.getProperty("user.home"), "data-e", "payments", fileName + ".pdf");
        Files.copy(file.getInputStream(),filePath);
        Student student = studentRepository.findByCode(code);
        Payment payment=Payment.builder().date(date).type(paymentType).amount(amount).student(student).file(filePath.toUri().toString()).status(PaymentStatus.CREATED).build();
        return paymentRepository.save(payment);
    }
    @GetMapping(path="/paymentFile/{paymentId}" ,produces=MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPaymentFile(@PathVariable Long paymentId) throws IOException {
        Payment payment = paymentRepository.findById(paymentId).get();
        return  Files.readAllBytes(Path.of(URI.create(payment.getFile())));
    }
}

