const React = require('react');
const ReactDOM = require('react-dom');

import UserProfile from './components/UserProfile';

const API_URL = 'http://localhost:8080/user-profiles/1';

class App extends React.Component {
    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
        this.state = {
            profileData: {},
            isLoading: false,
        };
    }

    componentDidMount() {
        this.setState({isLoading: true});
        fetch(API_URL)
            .then((response) => response.json())
            .then((data) => this.setState({profileData: data, isLoading: false}));
    }

    handleChange(changedProfile) {
        this.setState({profileData: changedProfile})
        fetch(API_URL, {
            method: "PUT",
            headers: { "Content-Type" : "application/json" },
            body:  JSON.stringify(changedProfile)
        }).then((data) => console.log(data));
    }

    render() {
        const {profileData, isLoading} = this.state;
        if (isLoading) {
            return <p>Loading ...</p>;
        }

        return (
            <UserProfile
                profileData={profileData}
                handleChange={this.handleChange}/>
        );
    }
}

ReactDOM.render(
    <App/>,
    document.getElementById('react')
);