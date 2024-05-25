package ma.emsi.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
		Tajine basicTajine = new BasicTajine();
		System.out.println("Description "+basicTajine.getDescription());
		System.out.println("Prix"+basicTajine.cost());

		basicTajine=new OliveDecorator(basicTajine);
		System.out.println("description "+basicTajine.getDescription());
		System.out.println("prix "+basicTajine.cost());

		basicTajine=new CitronDecorator(basicTajine);
		System.out.println(basicTajine.getDescription());
		System.out.println(basicTajine.cost());

		basicTajine=new FritesDecorator(basicTajine);
		System.out.println("description "+basicTajine.getDescription());
		System.out.println("prix "+basicTajine.cost());
	}
}
