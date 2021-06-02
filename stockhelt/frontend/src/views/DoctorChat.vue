<template>
  <v-card>
    <v-card-title>
      Chat
      <v-spacer></v-spacer>
      <v-btn @click="goAppointments">Appointments</v-btn>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="patients"
        @click:row="openChat"
    ></v-data-table>
    <DoctorTextDialog
        :opened="dialogVisible"
        :patient="selectedPatient"
        @refresh="refreshList"
    ></DoctorTextDialog>
  </v-card>
</template>

<script>
import api from "../api";
import DoctorTextDialog from "@/components/DoctorTextDialog";
import router from "@/router";

export default {
  name: "DoctorChat",
  components: {DoctorTextDialog},
  data() {
    return {
      patients: [],
      search: "",
      headers: [
        { text: "Patient's First Name", value: "firstName" },
        { text: "Patient's Last Name", value: "lastName" },
      ],
      dialogVisible: false,
      selectedPatient: {},
    };
  },
  props:{
    doctor:Object,
    opened: Boolean,
  },
  methods: {
    goAppointments() {
      router.push("/doctorsAppointmentList");
    },
    openChat(patient){
      this.selectedPatient = patient;
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.selectedPatient = {};
      this.patients = await api.users.allPatients();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
