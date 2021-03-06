
package com.coreprinciple.basic.autowired;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import com.coreprinciple.basic.AutoAppConfig;
import com.coreprinciple.basic.discount.DiscountPolicy;
import com.coreprinciple.basic.member.Grade;
import com.coreprinciple.basic.member.Member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AllBeanTest {
    

    @Test
    @DisplayName("모든 빈 조회")
    public void findAllBean() {
        
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        
        DiscountService discountService = ac.getBean(DiscountService.class);

        Member memberA = new Member(1L, "memberA", Grade.VIP);
    
        int discount = discountService.discount(memberA, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discount).isEqualTo(1000);

    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public DiscountService(Map<String, DiscountPolicy> policyMap,
                List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }
        
        public int discount(Member member, int price, String discountCode) {

            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            System.out.println("discountCode = " + discountCode);
            System.out.println("discountPolicy = " + discountPolicy);
            
            return discountPolicy.discount(member, price);
        }
}





}
