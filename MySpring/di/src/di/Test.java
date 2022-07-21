package di;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.my.dto.Product;
import com.my.exception.FindException;
import com.my.repository.ProductRepository;
import com.my.service.CustomerService;
import com.my.service.ProductService;

public class Test {

	public static void main(String[] args) {
		Product p1, p2 ;
		
		//	p1 = new Product(); (x)
		//	스프링 컨테이너 구동
		//	Product 객체 찾아 사용자에게 전달 -> 전달 시 필요한 파일 .xml (Configuration.xml)
		//	사용자의 입장에서 필요시마다 객체를 새로 생성하는 것이 아닌
		//	스프링에서 찾아 사용하도록 하는 것
		//	스프링 컨테이너가 공유 되어 있으면 사용자들이 같은 객체를 사용할 수 있는 것
		//	스프링 컨테이너가 구동 될 때 각 객체들은 싱글턴 패턴으로 미리 생성 되어 있음
		//	컨테이너에게 요청(객체찾기 작업)하여 해당 객체를 사용할 수 있도록 함 

		// 스프링 컨테이너 구동
		String configurationPath = "configuration2.xml";		
		ClassPathXmlApplicationContext ctx;
		ctx = new ClassPathXmlApplicationContext(configurationPath);

		// Product  객체 찾아 사용하기
		p1 = ctx.getBean("p",Product.class); // 첫번째 configuration의 name 속성(p) .두번째 캐스팅 가능한 자료형
		System.out.println(p1.hashCode()); // p 객체의 hashcode값만 가지고 옴
		System.out.println(p1);

		p2 = ctx.getBean("p",Product.class);
		System.out.println(p2.hashCode());
		System.out.println(p1 == p2);

		CustomerService service = ctx.getBean("customerService", CustomerService.class);
		System.out.println(service.hashCode());

		// 서비스에 주입된 리포지토리 얻기
		ProductService pService = ctx.getBean("productService", ProductService.class);
//		ProductRepository r1 = pService.getRepository();
		
		//	ProductOracleRepository r1  = pService.getRepository();
		//	getBean() -> 빈(Bean) 객체를 찾아 해당 객체가 형변환이 가능한가
		//	두번째 인자(ProductRepository를 사용하고 OracleRepository사용하지 않는 이유는 OracleRepository가 언제든지 바뀔 수 있기 때문)
		//	DB가 갑자기 변경되는 경우 (ProductOracleRepository -> ProductSQLRepository)
		//	다운캐스팅하여 OracleRepository 로 사용은 가능. 따라서 구체화된 클래스보다 인터페이스 사용하는 것이 더 좋음
		//	두번째 인자로 인터페이스를 사용하지 않는 경우 repository 형태가 변경될 시 테스트 코드가 또 바뀌어야함
		//	소스코드는 재컴파일 되지 않는 것이 좋음(소스코드 변경X)
		//	제공자 - 사용자 코드 결합도가 높으면 사용이 어려움 -> 사용 잘 안 하게 됨 -> 객체지향과 멀어지는 것

		//리포지토리 찾기
		ProductRepository r2 = ctx.getBean("productRepository", ProductRepository.class);
//		System.out.println(r1 ==r2);

		try {
			Product p = r2.selectByProdNo("C0001");
			System.out.println(p); //p.toString() 자동 호출됨
			// Console창에 NullPointerException이 날 때 연결이 안된 것. 의존관계 주입이 안된 것
		} catch (FindException e) {
			e.printStackTrace(); 
		}

	}

}
