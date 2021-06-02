import { saveAs } from 'file-saver';
export function saveFile(extension, response) {
    let currentDate = new Date();
    let datetime = currentDate.getDate() + "_"
        + (currentDate.getMonth()+1)  + "_"
        + currentDate.getFullYear() + "__"
        + currentDate.getHours() + "_"
        + currentDate.getMinutes() + "_"
        + currentDate.getSeconds();
    if (extension === "PDF") {
        let blob = new Blob([response.data], {type: "application/pdf"});
        saveAs(blob, "report_"+datetime + ".pdf");
    }
    else {
        let blob = new Blob([response.data], {type: "text/csv;charset=utf-8"});
        saveAs(blob, "report_"+datetime + ".csv");
    }
}
