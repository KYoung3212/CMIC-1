import React from 'react';
import ClayCard from '@clayui/card';
import ClayLoadingIndicator from '@clayui/loading-indicator';
import {Toast} from 'com.churchmutual.commons.web';
import AccountList from './AccountList';

class Accounts extends React.Component {

  constructor(props) {
    super(props);

    this.state = {
      accountsList: [],
      isLoading: true,
      filterBy: '',
      groupId: 0,
      searchTerm: '',
      toast:  {
        displayType: '',
        message: '',
        title: '',
      }
    };
  }

  componentDidMount() {
    this.getAccountsList();
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

  getAccountsList() {
    let callback = (data) => this.setState({accountsList: data, isLoading: false});

    let errCallback = () => this.displayErrorMessage('error.unable-to-retrieve-list-of-accounts');

    Liferay.Service(
      '/cmic.cmicaccountentry/get-cmic-account-entry-displays',
      callback,
      errCallback
    );
  }

  render() {
    return (
      <div className="account-list-portlet">
        <ClayCard>
          {this.state.isLoading ? (
            <ClayLoadingIndicator />
          ) : (
            <AccountList accountsList={this.state.accountsList} />
          )}
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


export default Accounts;
