<template>
  <div class="chat-container">
    <div class="heading">
<!--      <h1>{{title + '- User: ' + uuid }}</h1>-->
    </div>
    <div class="body">
      <div class="table">
        <chat-log></chat-log>
        <message-input></message-input>
      </div>
    </div>
  </div>
</template>

<script>
import ChatLog from '@/components/ChatLog';
import MessageInput from '@/components/MessageInput';
export default {
  name: 'ChatContainer',
  components: {
    ChatLog,
    MessageInput,
  },
  data() {
    return {
      title: 'Conversation',
    };
  },
};
</script>

<style scoped>
h1 {
  display: table-cell;
  vertical-align: middle;
  margin: 0;
  font-size: 16px;
  font-weight: normal;
}
.chat-container {
  display: table;
  max-width: 400px;
  min-width: 300px;
  width: 25%;
  height: 500px;
  margin: auto;
  background-color: #FFFFFF;
  border: solid 1px #BFBFBF;
  border-radius: 3px;
}
.heading {
  display: table;
  width: 100%;
  height: 28px;
  text-align: center;
  background-color: #CDCDCD;
  border-radius: 3px 3px 0 0;
  -ms-user-select: none;
  user-select: none;
  background: linear-gradient(#EDEDED 0%, #CDCDCD 75%);
}
.body {
  display: block;
  width: auto;
  height: inherit;
  overflow: hidden;
}
.table {
  display: table;
  width: 100%;
  height: 100%;
}
</style>
