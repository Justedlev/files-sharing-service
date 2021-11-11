import "./App.css";
import axios from "axios";
import fileDownload from "js-file-download";
import { useEffect, useState } from "react";

function App() {
  const root = "http://localhost:8080";
  const formData = new FormData();
  const [files, setFiles] = useState([]);

  async function onUpload(e) {
    e.preventDefault();
    axios.post(`${root}/upload`, formData, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    });
  }

  async function getData() {
    const response = await axios.get(root);
    console.log(response.data);
    setFiles(response.data);
  }

  async function download(filename) {
    await axios.get(`${root}/download/${filename}`).then((response) => fileDownload(response.data, filename));
  }

  useEffect(() => {
    getData();
  }, []);

  return (
    <div className="App">
      <div>
        Upload file: <input type="file" onChange={(e) => formData.append("file", e.target.files[0])} />
        <button onClick={onUpload}>Upload</button>
      </div>
      {files.map((f, i) => (
        <li key={i}>
          <h3>
            File name: <span>{f.name}</span>
          </h3>
          <h4>
            File size: <span>{f.size} byte</span>
          </h4>
          <button onClick={() => download(f.name)}>Download</button>
        </li>
      ))}
    </div>
  );
}

export default App;
