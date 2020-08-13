import React from 'react'

import UserProfileEditModal from "./UserProfileEditModal";

class UserProfile extends React.Component {
    constructor(props) {
        super(props);
        this.saveProfileData = this.saveProfileData.bind(this);
        this.state = {
            profileData: props.profileData,
            modalShow: false
        }
    }

    saveProfileData(profile) {
        this.setState({profileData: profile, modalShow: false});
        this.props.handleChange(profile)
    }

    render() {
        return (
            <div className="container">
                <div className="profile">
                    <div className="profile-header">
                        <h4 className="title">{this.state.profileData.name}'s UserProfile</h4>
                        <hr/>
                    </div>
                    <div className="profile-body">
                        <div className="modal-dialog-centered">
                            <img
                                src={"data:image/png;base64, " + this.state.profileData.avatarImage}  alt="avatarImage"
                                name="avatar" width="280" height="280" border="0" className="img-circle"/>
                        </div>
                        <hr/>
                        <form>
                            <div>
                                <p className="text-left"><strong>Name: </strong>
                                    {this.state.profileData.name}</p>
                            </div>
                            <div>
                                <p className="text-left"><strong>Link: </strong>
                                    <a href={this.state.profileData.link} target="_blank">{this.state.profileData.link}</a>.</p>
                            </div>
                            <div>
                                <p className="text-left"><strong>Description: </strong>
                                    {this.state.profileData.description}.</p>
                            </div>
                            <hr/>
                        </form>
                        <div className="footer">
                            <button type="button" className="btn btn-default" data-toggle="modal"
                                    data-target="#myModal" onClick={() => this.setState({modalShow: true})}>
                                Edit
                            </button>
                            <UserProfileEditModal
                                id={this.state.profileData.id}
                                name={this.state.profileData.name}
                                description={this.state.profileData.description}
                                avatarImage={this.state.profileData.avatarImage}
                                link={this.state.profileData.link}
                                saveProfileData={this.saveProfileData}/>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default UserProfile