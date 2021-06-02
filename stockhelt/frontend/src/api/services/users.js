import authHeader, { BASE_URL, HTTP } from "../http";
import {saveFile} from "@/api/utils";

export default {
  allUsers() {
    return HTTP.get(BASE_URL + "/users", { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
    allDoctors() {
        return HTTP.get(BASE_URL + "/users/doctors", { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    testEmail(user) {
        return HTTP.get(BASE_URL + `/sendemail/${user.id}`, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    allPatients() {
        return HTTP.get(BASE_URL + "/users/patients", { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
  create(user) {
    return HTTP.post(BASE_URL + "/users", user, { headers: authHeader() }).then(
        (response) => {
          return response.data;
        }
    );
  },
  edit(user) {
    return HTTP.put(BASE_URL + `/users/${user.id}`, user, { headers: authHeader() }).then(
        (response) => {
          return response.data;
        }
    );
  },
    delete(user) {
        return HTTP.delete(BASE_URL + `/users/${user.id}`, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    generateCSV() {
        return HTTP.get(BASE_URL + "/users/export/CSV", { responseType:"arraybuffer", headers: authHeader() }).then(
            (response) => {
                saveFile("CSV", response);
            }
        );
    },
    generatePDF() {
        return HTTP.get(BASE_URL + "/users/export/PDF", { responseType:"arraybuffer", headers: authHeader() }).then(
            (response) => {
                saveFile("PDF",response);
            }
        );
    },
};
