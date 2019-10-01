<template>
<div class="bg">
  <v-card style="width:50%; margin:auto; background-color:white">
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">Reservation the Field</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="4">
        <v-form v-model="valid" ref="form">
          <v-row justify="center">
            <v-col cols="10">
              <v-text-field
                outlined
                label="ID ผู้ให้บริการ"
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

          <!-- <div v-if="customerCheck"> -->
            <v-row>
              <v-col cols="10">
                <v-select
                  label="ประเภทสนาม"
                  outlined
                  v-model="reservation.fieldcategoryId"
                  :items="fieldCategory"
                  item-text="field"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ></v-select>
              </v-col>
            </v-row>
            <v-row justify="center">
              <v-col cols="20">
                <v-menu
                  ref="menu"
                  v-model="menu"
                  :close-on-content-click="false"
                  :return-value.sync="date"
                  transition="scale-transition"
                  offset-y
                  min-width="290px"
                  >
                  <template v-slot:activator="{ on }">
                    <v-text-field
                      v-model="date"
                      label="เลือกวันที่"
                      readonly
                      v-on="on"
                    ></v-text-field>
                  </template>
                  <v-date-picker v-model="date" @input="menu = false"></v-date-picker>
                </v-menu>
                <p>เวลา : {{date}}</p>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="10">
                <v-select
                  label="เลือกเวลา"
                  outlined
                  v-model="reservation.timetableId"
                  :items="timeTable"
                  item-text="timeString"
                  item-value="id"
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
          <!-- </div> -->
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
      date: new Date().toISOString().substr(0, 10),
      menu: false,
       reservation: {
        customerId: "",
        fieldcategoryId: "",
        timetableId: ""
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
            this.customerName = response.data.name;
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
    saveReservation() {
      http
        .post("/reservation/" +
            this.reservation.customerId +
            "/" +
            this.reservation.fieldcategoryId +
            "/" +
            this.reservation.timetableId,
          this.reservation
        )
        .then(response => {
          console.log(response);
          this.$router.push("/view");
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
    clear() {
      this.$refs.form.reset();
      this.customerCheck = false;
    },
    refreshList() {
      this.fieldCategory();
      this.timeTable();
    }
    /* eslint-enable no-console */
  },
  mounted() {
      this.fieldCategory();
      this.timeTable();
  }
}
</script>
<style>
 [v-cloak] { display: none; }
</style>