<template>
  <v-card>
    <v-card-title>
      Appointments
      <v-spacer></v-spacer>
      <v-text-field
        v-model="search"
        append-icon="mdi-magnify"
        label="Search"
        single-line
        hide-details
      ></v-text-field>
      <v-btn @click="goChat">Chat</v-btn>
    </v-card-title>
    <v-data-table
      :headers="headers"
      :items="appointments"
      :search="search"
      @click:row="editAppointment"
    ></v-data-table>
    <DoctorsAppointmentDialog
      :opened="dialogVisible"
      :appointment="selectedAppointment"
      @refresh="refreshList"
    ></DoctorsAppointmentDialog>
  </v-card>
</template>

<script>
import api from "../api";
import router from "@/router";
import DoctorsAppointmentDialog from "@/components/DoctorsAppointmentDialog";

export default {
  name: "DoctorsAppointmentList",
  components: { DoctorsAppointmentDialog },
  data() {
    return {
      appointments: [],
      search: "",
      headers: [
        { text: "Patient's First Name", value: "patient.firstName" },
        { text: "Patient's Last Name", value: "patient.lastName" },
        { text: "Date", value: "date" },
      ],
      dialogVisible: false,
      selectedAppointment: {},
    };
  },
  methods: {
    goChat(){
      router.push("/doctorChat");
    },
    editAppointment(appointment) {
      this.selectedAppointment = appointment;
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedAppointment = {};
      this.appointments = await api.appointments.allAppointments();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
