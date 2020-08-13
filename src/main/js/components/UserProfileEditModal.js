import React from 'react'
import ImageUploading from "react-images-uploading";

const MAX_NUMBER = 10;
const MAX_FILESIZE_MB = 3 * 1024 * 1024; // 3Mb

class UserProfileEditModal extends React.Component {
    constructor(props) {
        super(props);

        this.onUpload = this.onUpload.bind(this);
        this.handleSave = this.handleSave.bind(this);

        this.state = {
            id: props.id,
            name: props.name,
            description: props.description,
            avatarImage: props.avatarImage,
            avatarImageUrl: `data:image/png;base64,${props.avatarImage}`,
            link: props.link
        }
    }

    nameHandler(e) {
        this.setState({name: e.target.value});
    }

    descriptionHandler(e) {
        this.setState({description: e.target.value});
    }

    linkHandler(e) {
        this.setState({link: e.target.value});
    }

    handleSave() {
        this.props.saveProfileData(this.state)
    }

    onUpload(imageList) {
        let imageBase64 = imageList[0].dataURL.split(',').pop();

        this.setState({
            avatarImage: imageBase64,
            avatarImageUrl: imageList[0].dataURL
        });
    }

    onUploadError(errors, files) {
        console.log("onUploadError");
        console.log(errors, files);
    }

    render() {
        return (
            <div className="modal fade" id="myModal" tabIndex="-1" role="dialog" aria-labelledby="myModalLabel"
                 aria-hidden="true">
                <div className="modal-dialog">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h4 className="modal-title" id="myModalLabel">Edit {this.state.name}'s UserProfile</h4>
                        </div>
                        <div className="modal-body">
                            <div className="modal-dialog-centered">
                                <img
                                    src={this.state.avatarImageUrl} alt="avatarImage"
                                    name="avatar" width="280" height="280" border="0" className="img-circle"/>
                            </div>
                            <ImageUploading
                                onChange={this.onUpload}
                                maxNumber={MAX_NUMBER}
                                multiple={false}
                                maxFileSize={MAX_FILESIZE_MB}
                                acceptType={["jpg", "gif", "png"]}
                                onError={this.onUploadError}
                            >
                                {({ onImageUpload }) => (
                                    <button type="button" className="btn btn-default"
                                            onClick={onImageUpload}>Change Picture</button>
                                )}
                            </ImageUploading>
                            <hr/>
                            <form>
                                <div className="form-group">
                                    <label className="modal-label"
                                           htmlFor="nameInput"><strong>Name:</strong></label><br/>
                                    <input className="form-control" type="text" id="nameInput" value={this.state.name}
                                           onChange={(e) => this.nameHandler(e)}/>
                                </div>
                                <div className="form-group">
                                    <label className="modal-label"
                                           htmlFor="linkInput"><strong>Link:</strong></label><br/>
                                    <input className="form-control" type="text" id="linkInput" value={this.state.link}
                                           onChange={(e) => this.linkHandler(e)}/>
                                </div>
                                <div className="form-group">
                                    <label className="modal-label"
                                           htmlFor="descriptionTextarea"><strong>Description:</strong></label>
                                    <textarea className="form-control" id="descriptionTextarea" rows="3"
                                              value={this.state.description}
                                              onChange={(e) => this.descriptionHandler(e)}>
                                    </textarea>
                                </div>
                            </form>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="button" className="btn btn-default" data-dismiss="modal" onClick={() => {
                                this.handleSave()
                            }}>Save
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        )
    };
}

export default UserProfileEditModal