import React from 'react';
import ClayLoadingIndicator from '@clayui/loading-indicator';

const ProducerOrgList = (props) => {
  if (props.isLoading) {
    return (<ClayLoadingIndicator />);
  }
  else {
    return (
      <React.Fragment>
        {props.producerOrgList.slice(0, 3).map((producer, index) => (
          <div className="well well-lg" key={index}>
            <div>
              <h2 className="well-title">{producer.name}</h2>
              <div className="small font-weight-bold text-muted text-uppercase">{Liferay.Language.get('producer-code')}: {producer.divisionNumber}-{producer.agentNumber}</div>
            </div>
            <div>
              <React.Fragment>
                <div>{producer.addressLine1}</div>
                <div>{producer.addressLine2}</div>
                <div>{producer.city}, {producer.state} {producer.postalCode}</div>
              </React.Fragment>
              {producer.phoneNumber &&
                <div><a href={`tel:${producer.phoneNumber}`}>{producer.phoneNumber}</a></div>
              }
            </div>
          </div>
        ))}

        {props.producerOrgList.length > 3 &&
          <div className="bg-fade">
            <a className="link-action" href="javascript:;">{Liferay.Language.get('show-all')}</a>
          </div>
        }

        {props.producerOrgList.length == 0 &&
          <div className="well well-lg justify-content-around">
            <div className="empty-state">
              <h3 className="text-muted">
                {Liferay.Language.get('error.you-are-currently-not-associated-with-any-producer-organizations')}
              </h3>
              <div>
                {Liferay.Language.get('contact-your-organizations-administrator-to-be-invited-to-the-organization')}
              </div>
            </div>
          </div>
        }
      </React.Fragment>
    );
  }
};

export default ProducerOrgList;