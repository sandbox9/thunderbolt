package sandbox9.thunderbolt.eventstore.lego;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sandbox9.thunderbolt.Application;
import sandbox9.thunderbolt.entity.product.Product;
import sandbox9.thunderbolt.entity.product.Sku;
import sandbox9.thunderbolt.entity.product.repository.ProductRepository;

/**
 * Created by chanwook on 2014. 12. 6..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class LegoProductData {

    @Autowired
    MongoTemplate m;

    @Autowired
    ProductRepository r;

    public static final int LEGO_PRODUCT_1 = 75055;
    public static final int LEGO_PRODUCT_2 = 75021;
    public static final int LEGO_PRODUCT_3 = 75049;

    @Test
    public void createOneProductData() throws Exception {
        r.deleteAll();

        final int legoCategoryId = 100;
        final String manufacturer = "LEGO";

        // http://shop.lego.com/en-US/Imperial-Star-Destroyer-75055?p=75055&track=checkprice
        int skuId = 10001;
        String image1 = "http://cache.lego.com/r/www/r/starwars/-/media/franchises/lego%20starwars/products/75055/sw_1hy_75055_star_destroyer_compile_v004_1024k.mp4?l.r2=326351823";
        String image2 = "http://cache.lego.com/r/www/r/starwars/-/media/franchises/lego%20starwars/products/75055/prod_product_retina_1224x688_75055.jpg?l.r2=-1055819158";
        Product p = new Product(LEGO_PRODUCT_1, "임페리얼 스타 디스트로이어™", "",
                "제국의 힘을 느껴 보세요! 제국의 스타 디스트로이어™는 제국 함대의 상징적 함선이에요. 뒤쪽의 손잡이를 돌려 동시 작동되는 " +
                        "8개의 대포를 조준하고 본체 상단에 장착된 스프링식 슈터를 발사하세요. 상단 덮개를 들어 올리면 어디서도 볼 수 " +
                        "없는 팰퍼틴 황제의 홀로그램, 승무원용 회전 의자, 무기고, 함교, 제어판 등이 배치된 놀랍도록 정교한 실내가 드러납니다. " +
                        "이 멋진 모델을 이용해 클래식 스타워즈 영화의 시스 군주 다스 베이더™가 등장하는 장면을 재현해 보세요. " +
                        "놀이를 하거나 옮기기에 편리하도록 운반 손잡이가 달려 있습니다. " +
                        "다양한 무기로 무장한 미니피겨 6개(다스 베이더™, 제국군 장교, 스톰트루퍼™ 2개, 제국군 승무원, 제국군 네이비 트루퍼™)" +
                        "와 새롭고 독특한 피겨 2개(팰퍼틴 황제 홀로그램, 마우스 드로이드™)가 들어 있습니다.",
                manufacturer, legoCategoryId, new String[]{image1, image2});
        p.addSku(new Sku(skuId, "기본박스", "기본박스", 19999L, 100, 0));
        p.setStandardSkuId(skuId);
        r.save(p);

        skuId = 10002;
        image1 = "http://cache.lego.com/r/www/r/starwars/-/media/franchises/lego%20starwars/products/75021/prod_product_main_retina_1224x688_75021.jpg?l.r2=95154826";
        image2 = "http://cache.lego.com/r/www/r/starwars/-/media/franchises/lego%20starwars/products/75021/prod_packaging_main_retina_1224x688_75021.jpg?l.r2=1631631879";
        Product p2 = new Product(LEGO_PRODUCT_2, "리퍼블릭 건쉽™", "",
                "어마어마한 레고® 스타워즈™ 리퍼블릭 건쉽™을 타고 제노시스™로 달려가 수퍼 배틀 드로이드™들에게 제압당할 위기에 " +
                        "처한 오비완 캐노비, 아나킨 스카이워커, 파드메 아미달라를 구출하세요! 미니피겨 조종석에서 4구 포탑을 겨냥한 후," +
                        " 전면부를 열어 미사일을 발사하고 상부 장착형 발사장치 8개를 재장전하세요! " +
                        "측면과 후면 문을 열어 스피더 바이크를 출동시키세요! 휴대용 손잡이가 달려 있어 공중 액션 놀이를 더 쉽게 즐길 수 있습니다! " +
                        "오비완 캐노비, 아나킨 스카이워커, 파드메 아미달라, 클론 트루퍼™ 선장, 클론 트루퍼, " +
                        "수퍼 배틀 드로이드 2개 등 무기로 무장한 미니피겨 7개가 들어 있습니다.",
                manufacturer, legoCategoryId, new String[]{image1, image2});
        p2.addSku(new Sku(skuId, "기본박스", "기본박스", 12999L, 100, 0));
        p2.setStandardSkuId(skuId);
        r.save(p2);

        skuId = 10003;
        image1 = "http://cache.lego.com/r/www/r/starwars/-/media/franchises/lego%20starwars/products/75049/sw_1hy_75049_snowspeeder_compile_v005_1024k.mp4?l.r2=587623545";
        image2 = "http://cache.lego.com/r/www/r/starwars/-/media/franchises/lego%20starwars/products/75049/prod_packaging_retina_1224x688_75049.jpg?l.r2=1302580114";
        Product p3 = new Product(LEGO_PRODUCT_3, "스노우스피더™", "",
                "날렵한 스노우스피더™를 타고 루크 스카이워커™와 함께 로그 스쿼드론을 지휘하세요! 그와 포수 다크 랄터™를 조종석에 앉히고 " +
                        "양쪽 날개 아래에 숨겨진 스프링식 슈터를 이용해 공격을 펼치세요. " +
                        "후방의 스터드 블래스터로 제국의 스노우트루퍼™를 공격하거나 작살 갈고리로 바꿔 75054 AT-AT™(별매품)를 " +
                        "떨어뜨리세요. 이제 세발 거치대가 부착된 총을 들고 지상 전투를 벌일 차례예요. " +
                        "스타워즈 에피소드 V 제국의 역습에 등장하는 유명한 호스의 전투를 재현해 보세요! " +
                        "다양한 무기로 무장한 미니피겨 3개(루크 스카이워커™, 다크 랄터™, 스노우트루퍼™)가 들어 있습니다.",
                manufacturer, legoCategoryId, new String[]{image1, image2});
        p3.addSku(new Sku(skuId, "기본박스", "기본박스", 2999L, 100, 0));
        p3.setStandardSkuId(skuId);
        r.save(p3);
    }
}
