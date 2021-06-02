import authHeader, { BASE_URL, HTTP } from "../http";

export default {
    allPatients() {
        return HTTP.get(BASE_URL + "/users/patients", { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    create(patient) {
        return HTTP.post(BASE_URL + "/users/patients", patient, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    edit(patient) {
        return HTTP.put(BASE_URL + `/users/patients/${patient.id}`, patient, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    delete(patient) {
        return HTTP.delete(BASE_URL + `/users/patients/${patient.id}`, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    getFullPatient(patient) {
        return HTTP.get(BASE_URL + `/users/patients/${patient.id}`, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
};

