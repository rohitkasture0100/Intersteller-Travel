import React, { Component } from "react";
import axios from "axios";

class MyForm extends Component {
  constructor(props) {
    super(props);
    this.state = {
      client: {
        source: "Earth",
        destination: "",
        run: "Without Traffic",
      },
      server: {
        message: "",
        distance: 0,
        planetNames: [],
      },
    };
  }
  render() {
    const { client } = this.state;
    return (
      <div className="container">
        <div className="row">
          <div className="col-md-12">
            <div className="form-group files color">
              <label>Upload Supporting Data </label>
              <input
                type="file"
                className="form-control"
                name="file"
                onChange={this.onFileChangeHandler}
              />
            </div>
          </div>
        </div>
        <div className="row">
          <div className="col-md-4">
            <div className="form-group">
              <label> Source </label>
              <input
                type="text"
                className="form-control"
                name="source"
                disabled="true"
                value={client.source}
              />
            </div>
          </div>
          <div className="col-md-4">
            <div className="form-group ">
              <label> Destination </label>
              <input
                type="text"
                className="form-control"
                name="destination"
                onChange={(e) =>
                  this.setState({
                    client: {
                      source: client.source,
                      destination: e.target.value,
                      run: client.run,
                    },
                  })
                }
              />
            </div>
          </div>
          <div className="col-md-4">
            <div className="form-group ">
              <label> Traffic </label>
              <select
                className="form-control"
                name="run"
                onChange={(event) => {
                  this.setState({
                    client: {
                      run: event.target.value,
                      source: client.source,
                      destination: client.destination,
                    },
                  });
                }}
              >
                <option value="Without Traffic" selected>
                  Without Traffic
                </option>
                <option value="With Traffic">With Traffic</option>
                <option value="With Traffic">With Traffic Same Nodes</option>
              </select>
            </div>
          </div>
        </div>
        <div className="row">
          <div className="col-md-12">
            <div className="mb-2">
              <input
                type="button"
                className="btn btn-primary"
                name="cal"
                value="Calculate"
                onClick={this.calculate}
              />
            </div>
          </div>
        </div>
        <hr />
        <div className="row">
          <div className="col-md-12">
            <blockquote class="blockquote text-center">
              <p class="mb-0">
                {" "}
                {this.state.server.message
                  ? this.state.server.message +
                    " = " +
                    this.state.server.distance
                  : ""}{" "}
              </p>
              <blockquote class="blockquote text-center">
                {this.state.server.planetNames}
              </blockquote>
            </blockquote>
          </div>
        </div>
      </div>
    );
  }

  calculate = () => {
    const { client } = this.state;

    fetch("http://localhost:8084/calculate", {
      method: "post",
      headers: {
        "Content-Type": "application/json",
        Accept: "application/json",
      },
      body: JSON.stringify(client),
    })
      .then((res) => res.json())
      .then((res) => {
        this.setState({
          server: {
            message: res.message,
            distance: res.distance,
            planetNames: res.planetNames.join("->"),
          },
        });
      });
  };
  onFileChangeHandler = (e) => {
    e.preventDefault();
    this.setState({
      selectedFile: e.target.files[0],
    });
    const formData = new FormData();
    formData.append("file", e.target.files[0], e.target.files[0].name);
    fetch("http://localhost:8084/upload", {
      method: "post",
      body: formData,
    }).then((res) => {
      if (res.ok) {
        alert("File uploaded successfully.");
      }
    });
  };
}
export default MyForm;
