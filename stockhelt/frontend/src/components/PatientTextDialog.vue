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
  name: "PatientTextDialog",
  components:{ChatContainer},
  data(){
    return {
      send_message: null,
      connected: false,
    }
  },
  props: {
    doctor: Object,
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
        this.stompClient.send("/api/chat/topic/"+this.doctor.id, JSON.stringify(this.send_message),{});
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
              let data = JSON.parse(response.body);
              // this.$root.$emit("patientReceivedMessage",data);
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
      return !this.doctor.id;
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
    this.$root.$on('patientSendMessage', (messag) => {
      // your code goes here
      this.send(messag)
    })
  }
};
</script>

<style scoped></style>
