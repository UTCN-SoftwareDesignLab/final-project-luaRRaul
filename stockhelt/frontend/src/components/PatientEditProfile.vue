<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create patient" : "Edit patient" }}
        </v-toolbar>
        <v-form >
          <v-text-field v-model="patient.firstName" label="First Name" />
          <v-text-field v-model="patient.lastName" label="Last Name" />
          <v-text-field v-model="patient.email" label="Email" />
          <v-text-field v-model="patient.cnp" label="CNP" />
          <v-text-field v-model="patient.dateOfBirth" label="Date of Birth" />
          <v-text-field v-model="patient.address" label="Address" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "PatientEditProfile",
  data(){
    return {
    }
  },
  props: {
    patient: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
        api.patients
            .edit({
              id: this.patient.id,
              firstName: this.patient.firstName,
              lastName: this.patient.lastName,
              email: this.patient.email,
              cnp: this.patient.cnp,
              dateOfBirth: this.patient.dateOfBirth,
              address: this.patient.address,
            })
            .then(() => this.$emit("refresh"));
      }
    },
  computed: {
    isNew: function () {
      return false;
    },
  },
  watch:{
    opened: async function (newVal, oldVal) {
      if (newVal && !oldVal) {
        this.patient = await api.patients.getFullPatient({
          id: this.$store.state.auth.user.id
        });
      }
    }
  },
};
</script>

<style scoped></style>
