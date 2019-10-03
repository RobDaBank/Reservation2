<template>
  <b-container>
    <b-layout text-center wrap>
      <b-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">บันทึกเสร็จสิ้น</h1>
      </b-flex>
    </b-layout>
    <b-row justify="center">
      <b-col cols="8">
        <b-data-table :headers="headers" :items="items" :items-per-page="5" class="elevation-1">
        </b-data-table>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import http from "../http-common";
export default {
  name: "viewReservation",
  data() {
    return {
      headers: [
        {
          text: "Customer",
          align: "left",
          sortable: false,
          value: "customer.name"
        },
        { text: "FieldCategory", value: "fieldcategory.field" },
        { text: "Date", value: "date" },
        { text: "Time", value: "timetable.timeString" }
      ],
      items: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    getReservations() {
      http
        .get("/reservation")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.getReservations();
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getReservations();
  }
};
</script>
