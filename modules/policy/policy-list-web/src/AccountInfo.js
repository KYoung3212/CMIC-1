import React from 'react';
import ClayBreadcrumb from '@clayui/breadcrumb';

const AccountInfo = (props) => {
  const spritemap = Liferay.ThemeDisplay.getPathThemeImages() + '/clay/icons.svg';

  if (!props.isLoading) {
    return (
      <React.Fragment>
        <div>
          <div className="flex-container align-items-center">
            <h3 className="mb-0">{props.account.accountName}</h3>
            <small className="font-weight-bold text-muted ml-4">{props.account.accountNumber}</small>
          </div>
          <ClayBreadcrumb
            items={[
              {
                href: 'accounts',
                label: 'Accounts'
              },
              {
                active: true,
                label: `${props.account.accountName}`
              }
            ]}
            spritemap={spritemap}
            className="mb-sm-0 pb-0"
          />
        </div>
        <div className="well">
          <h6 className="well-title">{Liferay.Language.get('producer-org')}</h6>
          <div className="well-subtitle">{props.account.producerEntity}</div>
          <div className="small font-weight-bold text-muted">{props.account.producerEntityCode}</div>
        </div>
      </React.Fragment>
    );
  }
  else {
    return '';
  }
};

export default AccountInfo;