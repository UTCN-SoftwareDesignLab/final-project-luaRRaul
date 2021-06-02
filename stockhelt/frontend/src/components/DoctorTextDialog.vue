<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <ChatContainer></ChatContainer>
  </v-dialog>
</template>

<script>
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import ChatContainer from "@/components/ChatContainer";

export default {
  name: "DoctorTextDialog",
  components:{
    ChatContainer
  },
  data(){
    return {
      send_message: null,
      connected: false,
      // roles: ["Secretary", "Doctor", "Admin"],
    }
  },
  props: {
    patient: Object,
    opened: Boolean,
  },
  methods: {
    send(messag) {
      console.log("Send message:" + this.send_message);
      if (this.stompClient && this.stompClient.connected) {
        this.send_message = {
          type : "MSG",
          content: messag,
          sender: this.$store.state.auth.user.id
        };
        console.log(JSON.stringify(this.send_message));
        this.stompClient.send("/api/chat/topic/"+this.patient.id, JSON.stringify(this.send_message),{});
      }
    },
    connect() {
      this.socket = new SockJS("http://localhost:8088/api/my-websocket");
      this.stompClient = Stomp.over(this.socket);
      this.stompClient.connect(
          {},
          frame => {
            this.connected = true;
            console.log(frame);
            this.stompClient.subscribe("/api/topic/messages/"+this.$store.state.auth.user.id,function (response){
              console.log(response);
              let data = JSON.parse(response.body);
              // this.$root.$emit("doctorReceivedMessage",data);
              console.log(data);
            });
          },
          error => {
            console.log(error);
            this.connected = false;
          }
      );
    },
  },
  computed: {
    isNew: function () {
      return !this.patient.id;
    },
  },
  watch:{
    opened: async function (newVal, oldVal) {
      if (newVal && !oldVal) {
        this.connect();
      }
    }
  },
  mounted() {
    this.$root.$on('doctorSendMessage', (messag) => {
      // your code goes here
      this.send(messag)
    })
  }
};
</script>

<style scoped></style>
