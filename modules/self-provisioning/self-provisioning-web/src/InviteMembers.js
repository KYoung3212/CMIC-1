import React from 'react';
import ClayButton from '@clayui/button';
import {Input} from 'com.churchmutual.commons.web';

export default class extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      emails: '',
      showErrors: false
    };
    this.emailsInputRef = React.createRef();
  }

  clearMemberEmails() {
    this.setState({
      emails: '',
      showInviteMembers: false
    });
    this.emailsInputRef.current.value = '';
    this.setShowErrors(false);
    this.props.onClickCancel();
  }

  getUserId() {
    return Liferay.ThemeDisplay.getUserId();
  }

  setShowErrors(value) {
    this.setState({
      showErrors: value
    })
  }

  updateAccountUserEntries() {
    this.props.updateUserList();
  }

  updateInvitations(value) {
    if (this.state.showErrors) {
      this.setShowErrors(false);
    }

    this.setState({
      emails: value
    })
  }

  submit() {
    let callback = () => {
      this.setShowErrors(false);
      this.updateAccountUserEntries();
      this.clearMemberEmails();
      this.props.displaySuccessMessage('your-request-completed-successfully');
    }

    let errCallback = (message) => {
      if (message == '') {
        message = 'your-request-failed-to-complete';
      }

      this.props.displayErrorMessage(message)
    }

    Liferay.Service(
      '/cmic.cmicuser/invite-business-members',
      {
        groupId: this.props.groupId,
        emailAddresses: this.state.emails
      },
      callback,
      errCallback
    );
  }

  validateAndSubmit() {
    let emails = this.state.emails.split(',');

    for (const email of emails) {
      if (email.indexOf('@') < 0 || email.indexOf('.') < 0) {
        this.setShowErrors(true);
        return;
      }

      // check if multiple emails exist without a separator, denoted by special character @

      const re = new RegExp('@.*@');

      if (re.test(email)) {
        this.setShowErrors(true);
        return;
      }
    }

    this.submit();
  }

  render () {
    return this.props.visible && (
      <div className="row">
        <div className="col">
          <Input
            label={Liferay.Language.get('email-s')}
            fieldName="invitationEmailsInput"
            handleFieldChange={(fieldName, fieldValue) => this.updateInvitations(fieldValue)}
            showErrors={this.state.showErrors}
            errorMsg={Liferay.Language.get('please-enter-a-valid-email-address')}
            labelHint={Liferay.Util.sub(Liferay.Language.get('add-email-to-invite-user-to-x'), this.props.currentBusinessName)}
            inputRef={this.emailsInputRef}
          />
        </div>
        <div className="col-md-auto">
          <ClayButton.Group spaced>
            <ClayButton displayType="outline-secondary" className="btn-lg" onClick={() => this.clearMemberEmails()}>
              {Liferay.Language.get('cancel')}
            </ClayButton>
            <ClayButton displayType="primary" className="btn-lg" onClick={() => this.validateAndSubmit()}>
              {Liferay.Language.get('invite')}
            </ClayButton>
          </ClayButton.Group>
        </div>
      </div>
    );
  }
}
