package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {
    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // 무조건 EnumType.STRING 지정해야 함, 추후에 데이터가 추가될 경우 숫자로 들어가게 되면 꼬임
    private DeliveryStatus status; // 배송상태 : READY, COMP
}
