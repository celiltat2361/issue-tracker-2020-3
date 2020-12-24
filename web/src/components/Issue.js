import React,{useState} from 'react'
import EditForm from './EditForm'
import {Table, Card,Button, CardColumns, CardDeck} from 'react-bootstrap'

const Issue=({issue, handleDelete}) => {
  const [view,setView] = useState(false);
   

  return (
    <div>
      <CardDeck>
      <Card border="primary" style={{ width: '18rem' }}>
        <Card.Header as="h5">{issue.title}</Card.Header>
            {view? <EditForm key={issue.id} issue={issue} setView={setView} />:
            <Card.Body> 
             <Card.Title></Card.Title>
              <Card.Text> {issue.description} </Card.Text>
              <Card.Text>{issue.labels[0]}</Card.Text>
              <Button onClick={ () => setView(true)}>Edit </Button>
              <Button onClick={ () => handleDelete(issue.id)} variant="danger"  >Delete</Button>
              
           }
             
            </Card.Body>
            }
            </Card>
      </CardDeck>
      </div>
 
  );
}

export default Issue;
