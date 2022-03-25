import React, { useEffect, useState } from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import Container from 'react-bootstrap/Container';
import Carousel from 'react-bootstrap/Carousel';
import Navbar from 'react-bootstrap/Navbar'
import Nav from 'react-bootstrap/Nav';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Image from 'react-bootstrap/Image';
import Collapse from 'react-bootstrap/Collapse';

var swTitleFont = {
  font: "4em \"Lato\", sans-serif",
  color: "#FFE81F",
  marginBottom: "0.5em",
  marginTop: "0.5em"
};

var blackSWHolder = {
  backgroundColor: "black",
  borderStyle: "solid",
  borderColor: "#FFE81F black black black",
  borderWidth: "thin",
}

var ImgHeight = {
  width: "30%"
}

var swFont = {
  font: "1em \"Montserrat Light\", sans-serif",
  color: "#FFE81F"
}

var sithLords = {
  display: "flex",
  alignItems: "center",
  justifyContent: "center"
}

var sithLordsName = {
  textAlign: "center",
  font: "1em \"Pathway Gothic One'\", sans-serif",
  fontWeight: "bold",
  color: "#FFE81F"
}

ReactDOM.render(
  <>
    <DisplayHolder1 />
    <DisplayHolder2 />
    <DisplayHolder3 />
    <DisplayHolder4 />,
  </>,
  document.getElementById('root')
);


function NavControl() {
  return (
    <Container>
      <Navbar bg="dark" variant="dark" fixed="top">
        <Navbar.Brand href="#home">ThatPotatoBuild</Navbar.Brand>
        <Nav className="me-auto">
          <Nav.Link href="#home">Home</Nav.Link>
          <Nav.Link href="#features">Articles</Nav.Link>
          <Nav.Link href="#pricing">Potato Timeline</Nav.Link>
        </Nav>
      </Navbar>
    </Container>
  );
}


function DisplayHolder1() {
  // carousel

  return (
    <Carousel>
      <Carousel.Item>
        <img
          className="d-block w-100"
          src="/ep1.jpeg"
          alt="First slide"
          style={{ height: "75vmin" }}
        />
        <Carousel.Caption>
          <h3></h3>
          <p></p>
        </Carousel.Caption>
      </Carousel.Item>
      <Carousel.Item>
        <img
          className="d-block w-100"
          src="/ep2.jpeg"
          alt="Second slide"
          style={{ height: "75vmin" }}
        />

        <Carousel.Caption>
          <h3></h3>
          <p></p>
        </Carousel.Caption>
      </Carousel.Item>
      <Carousel.Item>
        <img
          className="d-block w-100"
          src="/star-wars-featured.jpeg"
          alt="Third slide"
          style={{ height: "75vmin" }}
        />

        <Carousel.Caption>
          <h3></h3>
          <p></p>
        </Carousel.Caption>
      </Carousel.Item>
    </Carousel>
    // </Container>
  );

}

function DisplayHolder2() {
  // introduction
  return (
    <Container fluid style={blackSWHolder}>
      <h1 className="text-center"
        style={swTitleFont} >THE OG. ALL IN ONE.</h1>
    </Container>
  );
}

function DisplayHolder3() {
  //details
  const [open, setCollapse] = useState(false);
  const [open2, setCollapse2] = useState(false);
  const [open3, setCollapse3] = useState(false);

  return (
    <Container fluid style={blackSWHolder}>
      <h1 className="text-center"
        style={swTitleFont} > RELIVE THE ANCIENT SITH.</h1>

      <Subtext2 />

      <p></p>
      <div style={{ margin: "0% 10% 10% 10%" }}>
        <Row>
          <NamedBaddies update={()=>setCollapse(!open), id="name1", image="malak.jpeg", description="test" } />

          {/* <Col sm={1} md={4}>
            <h2 style={sithLordsName}>Darth MALAK</h2>
            <div style={sithLords}>
              <Image src="/malak.jpeg"
                onClick={() => setCollapse(!open)}
                style={ImgHeight}
                aria-controls="name1"
                roundedCircle />
            </div>
            <Collapse in={open}>
              <div
                id="name1"
                style={swFont}>
                <i>
                  The Sith Lord, Darth Malak is a brute in the Force.
                  He uses his strength to overwhelm and destroy the Jedi Order.
                </i>
              </div>
            </Collapse>

          </Col> */}
          <Col sm={1} md={4}>

            <h2 style={sithLordsName}>Darth NIHLUS</h2>
            <div style={sithLords}>
              <Image src="/nihlus.jpeg"
                onClick={() => setCollapse2(!open2)}
                style={ImgHeight}
                aria-controls="name2"
                roundedCircle />
            </div>
            <Collapse in={open2}>
              <div id="name2" style={swFont}>
                <i>
                  Darth Nihlus is an aneathema to the Force.
                  He draws strength from others, feeding on them,until they expire.
                  He is less of a man then a force of nature.
                </i>
              </div>
            </Collapse>
          </Col>
          <Col sm={1} md={4}>
            <h2 style={sithLordsName}>Darth SION</h2>
            <div style={sithLords}>
              <Image src="/sion.jpeg"
                onClick={() => setCollapse3(!open3)}
                style={ImgHeight}
                aria-controls="name3"
                roundedCircle />
            </div>
            <Collapse in={open3}>
              <div id="name3" style={swFont}>
                <i>
                  The Lord of Pain brings his namesake whereever he goes.
                  A destroyer of life, he revels in the debauchery of death.
                </i>
              </div>
            </Collapse>
          </Col>
        </Row>
      </div>
    </Container>
  );
}

function DisplayHolder4() {
  //afterword`
  return (
    <Container fluid style={blackSWHolder}>
      <h1 className="text-center"
        style={swTitleFont}>COMPLETE YOUR MASTERY</h1>
    </Container>
  );
}

function Subtext1() {
  return (
    <>

    </>
  );
}

function Subtext2() {

  return (
    <>
      <div className="text-center" style={{
        color: "yellow",
        marginBottom: "5em",
        font: "0.9em \"ITC Franklin Gothic\", sans-serif"
      }}>
        <i>
          'It does not matter where we go. It is not the destination that matters. It is the journey.'
        </i>
      </div>
      <p>
      </p>
    </>
  );
}

function NamedBaddies(update, id, image,description) {

  return (
    <>
      <Col sm={1} md={4}>
        <h2 style={sithLordsName}>Darth MALAK</h2>
        <div style={sithLords}>
          <Image src= {image}
            onClick={update}
            style={ImgHeight}
            aria-controls= {id}
            roundedCircle />
        </div>
        <Collapse in={open}>
          <div
            id={id}
            style={swFont}>
            <i>
              {description}
            </i>
          </div>
        </Collapse>
      </Col>
    </>
  )
}
// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
