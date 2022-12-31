import './App.css';

function HomeView() {
  return (
    <div>
      <figure id="homeImageFigure">
        <img src={require("./resources/home_img.jpg")} alt="" />
        <figcaption id="homeImageCaption">Betting Assistant - the best tool for betting!</figcaption>
      </figure>
    </div>
  )
}

export default HomeView;