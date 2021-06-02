<template>
  <v-card>
    <v-card-title>
      Nothing
      <v-spacer></v-spacer>
      <v-btn @click="editInfo">Edit Profile</v-btn>
      <v-btn @click="openChat">Chat</v-btn>
      <v-data-table
          :headers="headers"
          :items="doctors"
          @click:row="openChat"
      ></v-data-table>
      <PatientTextDialog
          :opened="dialogVisible"
          :doctor="selectedDoctor"
          @refresh="refreshList"
      ></PatientTextDialog>
      <PatientEditProfile
          :opened="editVisible"
          @refresh="refreshList"
      ></PatientEditProfile>
    </v-card-title>
  </v-card>
</template>

<script>
import api from "../api";
import PatientTextDialog from "@/components/PatientTextDialog";
import PatientEditProfile from "@/components/PatientEditProfile";

export default {
  name: "PatientMain",
  components:{PatientTextDialog, PatientEditProfile},
  data(){
    return {
      doctors: [],
      headers:[
        {text:"Doctor name", value:"username"}
      ],
      visible: true,
      send_message: null,
      connected: false,
      dialogVisible:false,
      editVisible:false,
      selectedDoctor:{},
      selectedPatient:{},
    }
  },
  methods: {
    editInfo(){
      this.editVisible = true;
    },
    openChat(doctor){
      this.selectedDoctor = doctor;
      this.dialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.editVisible = false;
      this.selectedDoctor = {};
      this.selectedPatient = {};
      this.doctors = await api.users.allDoctors();
    },
  },
  created(){
    this.refreshList();
  },
};
</script>

<style scoped>

</style>