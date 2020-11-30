export class Address {

  id: number;
  address: String;
address2: String;
city: String;
state: String;
zip: number;
phone: number;

constructor(
  id?: number,
  address?: String,
address2?: String,
city?: String,
state?: String,
zip?: number,
phone?: number
){
 this.id= id;
 this.address= address;
this.address2= address2;
this.city= city;
this.state= state;
this.zip= zip;
this.phone= phone;
}

}
