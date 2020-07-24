import React from 'react';
import ClayCard from '@clayui/card';
import ClayLoadingIndicator from '@clayui/loading-indicator';
import {Toast} from 'com.churchmutual.commons.web';
import {ContactCards} from 'com.churchmutual.commons.web';

class Contacts extends React.Component {

  constructor(props) {
    super(props);

    this.state = {
      contactsList: [],
      isLoading: true,
      groupId: 0,
      toast:  {
        displayType: '',
        message: '',
        title: '',
      }
    };
  }

  componentDidMount() {
    this.getContactsList();
  }

  displayErrorMessage(msg) {
    this.setState({
      toast: {
        displayType: 'danger',
        message: msg,
        title: Liferay.Language.get('error-colon')
      }
    });
  }

  onToastClosed() {
    this.setState({
      toast: {
        displayType: '',
        message: '',
        title: ''
      }
    });
  }

  getContactsList() {
    /*fetch(`/o/contact/contacts/${this.userId}`)
      .then(res => res.json())
      .then(data => {
        let accounts = data;
        this.setState({contactsList: accounts, isLoading: false});
      })
      .catch(() => this.displayErrorMessage('error.unable-to-retrieve-list-of-contacts'))*/

    this.setState({
      contactsList: [
        { firstName: 'Karlie', lastName: 'Schowalter', fullName: 'Karlie Schowalter', title: 'Producer Code: 35-000, 35-001, 35-858', email: 'karlie.schowalter@churchmutal.com', phoneNumber: '715-555-5555' },
        { firstName: 'Doris', lastName: 'O’Connell', fullName: 'Doris O’Connell', title: 'Producer Code: 49-064', email: 'doris.oconnell@churchmutal.com', phoneNumber: '715-555-5555' },
        { firstName: 'Elouise', lastName: 'Hintz', fullName: 'Elouise Hintz', title: 'Producer Code: 49-334', email: 'elouise.hintz@churchmutal.com', phoneNumber: '715-555-5555' },
        { firstName: 'Karlie', lastName: 'Schowalter', fullName: 'Karlie Schowalter', title: 'Producer Code: 35-000, 35-001, 35-858', email: 'karlie.schowalter@churchmutal.com', phoneNumber: '715-555-5555' }
      ],
      isLoading: false
    });
  }

  render() {
    return (
      <div className="contact-producer-dashboard-portlet">
        <ClayCard>
          <div className="card-header">
            <ClayCard.Description displayType="title">{Liferay.Language.get('territory-managers')}</ClayCard.Description>
          </div>
          <ClayCard.Body>
            {this.state.isLoading ? (
              <ClayLoadingIndicator />
              ) : (
              <ContactCards
                list={this.state.contactsList}
                isLoading={this.state.isLoading}
                limit={3}
                pathname="contact"
                md={6}
                lg={12}
              />
            )}
          </ClayCard.Body>
        </ClayCard>

        <Toast
          displayType={this.state.toast.displayType}
          message={this.state.toast.message}
          onClose={() => this.onToastClosed()}
          title={this.state.toast.title} />
      </div>
    );
  }
};


export default Contacts;
