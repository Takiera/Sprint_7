package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderModel {

        public String firstName;
        public String lastName;
        public String address;
        public int metroStation;
        public String phone;
        public int rentTime;
        public String deliveryDate;
        public String comment;
        public String[] color;

}