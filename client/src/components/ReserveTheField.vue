<template>
<div class="bg">
  <v-card style="width:50%; margin:auto; background-color:white">
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">แบบฟอร์มบันทึกการจองสนามกีฬา</h1>
      </v-flex>
    </v-layout>
    
    <v-row justify="center">
      <v-col cols="4">
        <v-form v-model="valid" ref="form">
          <v-row justify="center">
            <v-col cols="10">
              <v-text-field
                outlined
                label="ID ผู้ใช้บริการ"
                v-model="reservation.customerId"
                :rules="[(v) => !!v || 'Item is required']"
                required
              ></v-text-field>
              <p v-if="customerCheck != ''">ยินดีตอนรับคุณ : {{customerName}}</p>
            </v-col>
            <v-col cols="2">
              <div class="my-2">
                <v-btn @click="findCustomer" depressed large color="green">ยืนยัน</v-btn>
              </div>
            </v-col>
          </v-row>

          <v-row>
              <v-col cols="10">
                <v-select
                  label="พนักงานที่ทำรายการ"
                  outlined
                  v-model="reservation.employeeId"
                  :items="employee"
                  item-text="employeename"
                  item-value="employeeid"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
              </v-col>
            </v-row>

            <v-row>
              <v-col cols="10">
                <v-select
                  label="เลือกประเภทสนาม"
                  outlined
                  v-model="reservation.fieldcategoryId"
                  :items="fieldCategory"
                  item-text="field"
                  item-value="fieldid"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
              </v-col>
            </v-row>
            <v-row justify="center">
              <v-col cols="20">
                <v-menu
        v-model="menu"
        :close-on-content-click="false"
        :nudge-right="40"
        transition="scale-transition"
        offset-y
        min-width="290px"
      >
        <template v-slot:activator="{ on }">
          <v-text-field
            v-model="reservedate"
            label="เลือกวันที่จองสนาม"
            readonly
            v-on="on"
          ></v-text-field>
        </template>
        <v-date-picker v-model="reservedate" @input="menu = false"></v-date-picker>
      </v-menu>

              </v-col>
            </v-row>
            <v-row>
              <v-col cols="10">
                <v-select
                  label="เลือกเวลาจองสนาม"
                  outlined
                  v-model="reservation.timetableId"
                  :items="timeTable"
                  item-text="timeString"
                  item-value="timetableid"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
              </v-col>
            </v-row>
            <v-row justify="center">
              <v-col cols="12">
                <v-btn @click="saveReservation" :class="{ red: !valid, green: valid }">บันทึก</v-btn>
                <v-btn style="margin-left: 15px;" @click="clear">ยกเลิก</v-btn>
              </v-col>
            </v-row>
            <br />
        </v-form>
      </v-col>
    </v-row>
  </v-container>
  </v-card>
  </div>
</template>

<script>
import http from "../http-common";
export default {
  name: 'reserveTheField',
  components: {
    },
   data () {
    return { 
      reservedate: new Date().toISOString().substr(0, 10),
      menu: false,
       reservation: {
        customerId: "",
        fieldcategoryId: "",
        timetableId: "",
        employeeId: ""
      },
      valid: false,
      customerCheck: false,
      customerName: "",
    }
  },
  methods: {
    /* eslint-disable no-console */

    fieldCategory(){
      http
        .get("/fieldcate")
        .then(response =>{
            this.fieldCategory = response.data;
            console.log(response.data)
        })
        .catch(e => {
            console.log(e);
        })
    },
    employee(){
      http
        .get("/employee")
        .then(response =>{
            this.employee = response.data;
            console.log(response.data)
        })
        .catch(e => {
            console.log(e);
        })
    },
    timeTable() {
      http
        .get("/timetable")
        .then(response => {
          this.timeTable = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        })
    },
    findCustomer() {
      http
        .get("/customer/" + this.reservation.customerId)
        .then(response => {
          console.log(response);
          if (response.data != null) {
            this.customerName = response.data.customername;
            this.customerCheck = response.status;
          } else {
            this.clear()
          }          
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
    zipData(cus, field, times, emp, val) {
      let datazip = {
        customerId: cus,
        fieldcateId: field,
        timeableId: times,
        employeeId: emp,
        reservedate: val
      };
      return datazip;
    },
    saveReservation() {
      http
        .post(
          "/reservation/create",
          this.zipData(
            Number(this.reservation.customerId),
            Number(this.reservation.fieldcategoryId),
            Number(this.reservation.timetableId),
            Number(this.reservation.employeeId),
            this.reservedate
          )
        )
        .then(response => {
          console.log(response);
          this.$router.push("/view");
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
       alert("55");
    },
    clear() {
      this.$refs.form.reset();
      this.customerCheck = false;
    },
    refreshList() {
      this.fieldCategory();
      this.timeTable();
      this.employee();
    }
    /* eslint-enable no-console */
  },
  mounted() {
      this.fieldCategory();
      this.timeTable();
      this.employee();
  }
}
</script>
<style>
 [v-cloak] { display: none; }
</style>