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
      <v-btn @click="goPatients">Patient List</v-btn>
      <v-btn @click="addAppointment">Create Appointment</v-btn>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="appointments"
        :search="search"
        @click:row="editAppointment"
    ></v-data-table>
    <SecretaryAppointmentDialog
        :opened="dialogVisible"
        :appointment="selectedAppointment"
        @refresh="refreshList"
    ></SecretaryAppointmentDialog>
  </v-card>
</template>

<script>
import api from "../api";
import SecretaryAppointmentDialog from "@/components/SecretaryAppointmentDialog";
import router from "@/router";

export default {
  name: "SecretaryAppointmentList",
  components: { SecretaryAppointmentDialog},
  data() {
    return {
      appointments: [],
      search: "",
      headers: [
        { text: "Patient's First Name", value: "patient.firstName" },
        { text: "Patient's Last Name", value: "patient.lastName" },
        { text: "Doctor's name", value: "doctor.username" },
        { text: "Date", value: "date" },
      ],
      dialogVisible: false,
      selectedAppointment: {},
    };
  },
  methods: {
    goPatients(){
      router.push("/patientList")
    },
    addAppointment(){
      this.dialogVisible = true;
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
