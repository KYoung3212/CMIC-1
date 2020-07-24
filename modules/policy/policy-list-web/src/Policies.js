import React from 'react';
import ReactDOM from 'react-dom';
import ClayCard from '@clayui/card';
import {Toast, CardPageHeader} from 'com.churchmutual.commons.web';
import AccountInfo from './AccountInfo';
import PolicyList from './PolicyList';
import PolicySearch from './PolicySearch';

class Policies extends React.Component {

  constructor(props) {
    super(props);

    this.state = {
      account: {},
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
    this.getAccount();
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

  getAccount() {
    let cmicAccountEntryId = this.getCMICAccountEntryId()

    if (cmicAccountEntryId == null) {
      this.setState({isLoading: false});

      return;
    }

    let errCallback = () => this.displayErrorMessage('error.unable-to-retrieve-list-of-policies')

    let accountCallback = (account) => {
      this.setState({
        account: {
          accountName: account.accountName,
          accountNumber: account.accountNumber,
          companyNumber: account.companyNumber,
          producerEntity: account.producerName,
          producerEntityCode: account.producerCode
        }
      });

      this.getPolicies()
    }

    Liferay.Service(
      '/cmic.cmicaccountentry/get-cmic-account-entry-display',
      {
        cmicAccountEntryId: cmicAccountEntryId
      },
      accountCallback,
      errCallback
    );
  }

  getCMICAccountEntryId() {
    let urlSearchParameters = new URLSearchParams(window.location.search);

    return urlSearchParameters.get('cmicAccountEntryId');
  }

  getPolicies() {
    let errCallback = () => this.displayErrorMessage('error.unable-to-retrieve-list-of-policies')

    let policyListCallback = (policies) => {
      this.setState((prevState) => ({
        account: {
          ...prevState.account,
          policyList: policies
        },
        isLoading: false
      }));

      this.addRecentlyViewedCMICAccountEntryId();
    }

    Liferay.Service(
      '/cmic.cmicpolicy/get-policy-displays',
      {
        cmicAccountEntryId: this.getCMICAccountEntryId()
      },
      policyListCallback,
      errCallback
    );
  }

  addRecentlyViewedCMICAccountEntryId() {
    Liferay.Service(
      '/cmic.cmicuser/add-recently-viewed-cmic-account-entry-id',
      {
        cmicAccountEntryId: this.getCMICAccountEntryId()
      }
    );

  }

  render() {
    return (
      <div className="policy-list-portlet">
        <CardPageHeader>
            <AccountInfo
              account={this.state.account}
              isLoading={this.state.isLoading}
            />
        </CardPageHeader>
        <ClayCard>
          <div className="card-header flex-container flex-container-stacked-xs justify-content-between align-items-md-center">
            <ClayCard.Description displayType="title">{Liferay.Language.get('policies')}</ClayCard.Description>
            <div className="mt-4 mt-md-0">
              <PolicySearch
                isLoading={this.state.isLoading}
              />
            </div>
          </div>
          <ClayCard.Body>
            <PolicyList
              policyList={this.state.account.policyList}
              isLoading={this.state.isLoading}
            />
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


export default Policies;
