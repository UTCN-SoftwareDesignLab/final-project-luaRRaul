<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create user" : "Edit user" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="user.username" label="Username" />
          <v-text-field v-model="user.email" label="Email" />
          <v-text-field v-model="user.password" label="Password" />
          <v-select
              label="Role"
            item-text="role"
            v-model="user.role"
            :items="roles">
          </v-select>
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
          <v-btn @click="delet">
            Delete
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";
export default {
  name: "UserDialog",
  props: {
    user: Object,
    opened: Boolean,
  },
  methods: {
    delet(){
      api.users.delete({
        id: this.user.id,
      }).then(() => this.$emit("refresh"));
    },
    persist() {
      if (this.isNew) {
        api.users
            .create({
              username: this.user.username,
              email: this.user.email,
              password: this.user.password,
              role: this.user.role,
            })
            .then(() => this.$emit("refresh"));
      } else {
        api.users
            .edit({
              id: this.user.id,
              username: this.user.username,
              email: this.user.email,
              role: this.user.role,
            })
            .then(() => this.$emit("refresh"));
      }
    },
  },
  computed: {
    isNew: function () {
      return !this.user.id;
    },
  },
  data() {
    return {
      roles: ["Secretary", "Doctor", "Admin", "Patient"],
    }
  }
};
</script>

<style scoped></style>
